package com.tavant.mobilecoe.bubblegame.gameobject;

import static android.opengl.GLES10.GL_FLOAT;
import static android.opengl.GLES10.GL_TEXTURE_2D;
import static android.opengl.GLES10.GL_TEXTURE_COORD_ARRAY;
import static android.opengl.GLES10.glEnable;
import static android.opengl.GLES10.glTexCoordPointer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Water extends GameObject {

	private  FloatBuffer vertexBuffer;
	private  FloatBuffer normalBuffer;  // this is for lightining
	private  ShortBuffer drawListBuffer;
	private FloatBuffer mTexBuffer;
	private static final int COORDS_PER_VERTEX = 3;
	private static float squareCoords[] = {
		-1.5f,  0.4f, 0.0f,   // top left
		-1.2f, -0.4f, 0.0f,   // bottom left
		1.2f, -0.4f, 0.0f,   // bottom right
		1.5f,  0.4f, 0.0f }; // top right

	private final short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices
	float color[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };

	private final float normalchord[]= { // front
					 0, 1, 0,
					 0, 1, 0,
					 0, 1, 0,
					 0, 1, 0
		};

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
         
         ByteBuffer buff = ByteBuffer.allocateDirect(normalchord.length * 4);
         buff.order(ByteOrder.nativeOrder());
         normalBuffer=buff.asFloatBuffer();
         normalBuffer.put(normalchord);
         normalBuffer.position(0);
	}

	

	public void draw(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glVertexPointer( // point to vertex data:
				COORDS_PER_VERTEX,
				GL10.GL_FLOAT, 0, vertexBuffer);
        glTexCoordPointer(2, GL_FLOAT, 0, mTexBuffer);
        gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
        gl.glDrawElements(  // draw shape:
				GL10.GL_TRIANGLES,
				drawOrder.length, GL10.GL_UNSIGNED_SHORT,
				drawListBuffer);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL_TEXTURE_2D);
	}

}
