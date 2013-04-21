package mffs.item.mode;

import java.util.Set;

import mffs.api.IProjector;
import mffs.render.model.ModelPlane;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import universalelectricity.core.vector.Vector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemModeTube extends ItemModeCube
{
	public ItemModeTube(int i)
	{
		super(i, "modeTube");
	}

	@Override
	public void calculateField(IProjector projector, Set<Vector3> fieldBlocks)
	{
		ForgeDirection direction = projector.getDirection(((TileEntity) projector).worldObj, ((TileEntity) projector).xCoord, ((TileEntity) projector).yCoord, ((TileEntity) projector).zCoord);
		Vector3 posScale = projector.getPositiveScale();
		Vector3 negScale = projector.getNegativeScale();

		for (float x = -negScale.intX(); x <= posScale.intX(); x += 0.5f)
		{
			for (float z = -negScale.intZ(); z <= posScale.intZ(); z += 0.5f)
			{
				for (float y = -negScale.intY(); y <= posScale.intY(); y += 0.5f)
				{
					if (!(direction == ForgeDirection.UP || direction == ForgeDirection.DOWN) && (y == -negScale.intY() || y == posScale.intY()))
					{
						fieldBlocks.add(new Vector3(x, y, z));
						continue;
					}

					if (!(direction == ForgeDirection.NORTH || direction == ForgeDirection.SOUTH) && (z == -negScale.intZ() || z == posScale.intZ()))
					{
						fieldBlocks.add(new Vector3(x, y, z));
						continue;
					}

					if (!(direction == ForgeDirection.WEST || direction == ForgeDirection.EAST) && (x == -negScale.intX() || x == posScale.intX()))
					{
						fieldBlocks.add(new Vector3(x, y, z));
						continue;
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(IProjector projector, double x, double y, double z, float f, long ticks)
	{
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		GL11.glTranslatef(-0.5f, 0, 0);
		ModelPlane.INSTNACE.render();
		GL11.glTranslatef(1f, 0, 0);
		ModelPlane.INSTNACE.render();
		GL11.glTranslatef(-0.5f, 0f, 0);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glTranslatef(0.5f, 0f, 0f);
		ModelPlane.INSTNACE.render();
		GL11.glTranslatef(-1f, 0f, 0f);
		ModelPlane.INSTNACE.render();
	}
}