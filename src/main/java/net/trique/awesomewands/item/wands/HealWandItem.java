package net.trique.awesomewands.item.wands;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.trique.awesomewands.particle.AwesomeParticles;

import java.util.HashSet;
import java.util.Set;

public class HealWandItem extends Item {
    public HealWandItem(Properties props) {
        super(props.attributes(createAttributeModifiers()));
    }

    private static ItemAttributeModifiers createAttributeModifiers() {
        ItemAttributeModifiers.Builder b = ItemAttributeModifiers.builder();
        return b.build();
    }

    @Override
    public boolean isEnchantable(ItemStack stack) { return true; }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(Items.ICE);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) { return UseAnim.BOW; }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 20;
    }

    @Override
    public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.onUseTick(level, user, stack, remainingUseTicks);
        if (getUseDuration(stack, user) - remainingUseTicks == 1) {
            level.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ALLAY_AMBIENT_WITH_ITEM, SoundSource.PLAYERS, 3.0F, 1.0F);
            level.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 5.0F, 1.0F);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        if (!level.isClientSide && user instanceof Player player) {
            boolean creative = player.getAbilities().instabuild;

            ItemStack chargeResource = findChargeResource(player);
            if (creative || !chargeResource.isEmpty()) {
                spawnHealBeam(level, user);

                if (!creative) {
                    chargeResource.shrink(1);
                    player.getCooldowns().addCooldown(this, 40);
                    stack.hurtAndBreak(1, user, EquipmentSlot.MAINHAND);
                }
            }
        }
        return super.finishUsingItem(stack, level, user);
    }

    private ItemStack findChargeResource(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack s = player.getInventory().getItem(i);
            if (s.is(Items.AMETHYST_SHARD)) return s;
        }
        return ItemStack.EMPTY;
    }

    private void spawnHealBeam(Level level, LivingEntity user) {
        level.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ALLAY_ITEM_GIVEN, SoundSource.PLAYERS, 3.0F, 1.0F);
        level.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 5.0F, 1.0F);

        final float heightOffset = 1.6f;
        final int distance = 20;

        Vec3 source = user.position().add(0.0, heightOffset, 0.0);
        Vec3 target = user.position().add(user.getLookAngle().scale(distance));
        Vec3 toTarget = target.subtract(source);
        Vec3 dir = toTarget.normalize();

        Set<Entity> hit = new HashSet<>();
        int steps = Mth.floor(toTarget.length()) + 7;

        for (int i = 1; i < steps; i++) {
            Vec3 p = source.add(dir.scale(i));

            if (level instanceof ServerLevel sl) {
                sl.sendParticles(AwesomeParticles.HEAL_BEAM.get(), p.x, p.y, p.z, 1, 0, 0, 0, 0);
            }

            BlockPos bp = BlockPos.containing(p);
            AABB box = new AABB(bp).inflate(1.0);
            hit.addAll(level.getEntitiesOfClass(LivingEntity.class, box, e -> {
                if (e == user) return false;
                return (e instanceof Player) || (e instanceof Animal);
            }));
        }

        final float healPerTarget = 4.0F;
        for (Entity e : hit) {
            if (e instanceof LivingEntity living) {
                living.heal(healPerTarget);

                level.playSound(null, living.getX(), living.getY(), living.getZ(),
                        SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 3.0F, 1.0F);
            }
        }
    }
}