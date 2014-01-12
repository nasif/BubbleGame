package com.tavant.mobilecoe.bubblegame.gameobject;

import static android.opengl.GLES10.GL_FLOAT;
import static android.opengl.GLES10.GL_TEXTURE_2D;
import static android.opengl.GLES10.glEnable;
import static android.opengl.GLES10.glTexCoordPointer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Water extends GameObject {

	private  FloatBuffer vertexBuffer;
	private  ShortBuffer drawListBuffer;
	private FloatBuffer mTexBuffer;
	private static final int COORDS_PER_VERTEX = 3;
	private static float squareCoords[] = {
		-1.0f,  0.2f, 0.0f,   // top left
		-1.0f, -0.2f, 0.0f,   // bottom left
		1.0f, -0.2f, 0.0f,   // bottom right
		1.0f,  0.2f, 0.0f }; // top right

	private final short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices
	float color[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };


	public Water() {
		ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(squareCoords);
		vertexBuffer.position(0);

		// initialize byte buffer for the draw list
		ByteBuffer dlb = ByteBuffer.allocateDirect(
				// (# of coordinate values * 2 bytes per short)
				drawOrder.length * 2);
		dlb.order(ByteOrder.nativeOrder());
		drawListBuffer = dlb.asShortBuffer();
		drawListBuffer.put(drawOrder);
		drawListBuffer.position(0);
		
		 ByteBuffer tbb = ByteBuffer.allocateDirect(4 * 2 * 4);
         tbb.order(ByteOrder.nativeOrder());
         mTexBuffer = tbb.asFloatBuffer();
         for (int i = 0; i < 4; i++) {
             for(int j = 0; j < 2; j++) {
                 mTexBuffer.put(squareCoords[i*3+j] * 2.0f + 0.5f);
             }
         }
         mTexBuffer.position(0);
	}

	

	public void draw(GL10 gl) {
		gl.glVertexPointer( // point to vertex data:
				COORDS_PER_VERTEX,
				GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glEnable(GL_TEXTURE_2D);
        glTexCoordPointer(2, GL_FLOAT, 0, mTexBuffer);
		gl.glDrawElements(  // draw shape:
				GL10.GL_TRIANGLES,
				drawOrder.length, GL10.GL_UNSIGNED_SHORT,
				drawListBuffer);

		// Disable vertex array drawing to avoid
		// conflicts with shapes that don't use it
		gl.glDisable(GL_TEXTURE_2D);
		
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		//gl.glDisable(GL10.GL_CULL_FACE);
	}

}
