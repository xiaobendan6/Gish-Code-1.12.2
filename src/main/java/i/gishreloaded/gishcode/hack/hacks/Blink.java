package i.gishreloaded.gishcode.hack.hacks;

import i.gishreloaded.gishcode.hack.Hack;
import i.gishreloaded.gishcode.hack.HackCategory;
import i.gishreloaded.gishcode.utils.Entity301;
import i.gishreloaded.gishcode.utils.system.Wrapper;
import i.gishreloaded.gishcode.utils.system.Connection.Side;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketPlayer;

public class Blink extends Hack{

	public Entity301 entity301 = null;
	
	public Blink() {
		super("Blink", HackCategory.PLAYER);
	}

	@Override
	public boolean onPacket(Object packet, Side side) {
		return !(side == i.gishreloaded.gishcode.utils.system.Connection.Side.OUT && (packet instanceof CPacketPlayer
                || packet instanceof CPacketPlayer.Position
                || packet instanceof CPacketPlayer.Rotation
                || packet instanceof CPacketPlayer.PositionRotation));
	}
	
	@Override
	public void onEnable() {
		if (Wrapper.INSTANCE.player() != null && Wrapper.INSTANCE.world() != null) {
            this.entity301 = new Entity301(Wrapper.INSTANCE.world(), Wrapper.INSTANCE.player().getGameProfile());
            this.entity301.setPosition(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY, Wrapper.INSTANCE.player().posZ);
            this.entity301.inventory = Wrapper.INSTANCE.player().inventory;
            this.entity301.rotationPitch = Wrapper.INSTANCE.player().rotationPitch;
            this.entity301.rotationYaw = Wrapper.INSTANCE.player().rotationYaw;
            this.entity301.rotationYawHead = Wrapper.INSTANCE.player().rotationYawHead;
            Wrapper.INSTANCE.world().spawnEntity(this.entity301);
        }
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		if (this.entity301 != null && Wrapper.INSTANCE.world() != null) {
            Wrapper.INSTANCE.world().removeEntity((Entity) this.entity301);
            this.entity301 = null;
        }
		super.onDisable();
	}
}
