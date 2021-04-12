import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL
import java.lang.Math.PI
import java.lang.RuntimeException
import kotlin.math.cos
import kotlin.math.sin

private var window: Long = 0
private var dots = arrayListOf<Dot>()

fun main() {

	init()
	
	for (i in 0 until Dot.Settings.dotAmount) {
		dots.add(Dot())
	}
	
	loop()
	
	glfwFreeCallbacks(window)
	glfwDestroyWindow(window)
	
	glfwTerminate()
	glfwSetErrorCallback(null)?.free()
}

fun init() {
	GLFWErrorCallback.createPrint(System.err).set()
	
	if (!glfwInit()) {
		throw IllegalStateException("No GLFW")
	}
	
	glfwDefaultWindowHints()
	glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
	glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)
	
	window = glfwCreateWindow(Dot.Settings.windowSize, Dot.Settings.windowSize, "Particles", NULL, NULL)
	
	if (window == NULL) {
		throw RuntimeException("no window")
	}
	
	glfwMakeContextCurrent(window)
	
	glfwSwapInterval(1)
	
	glfwShowWindow(window)
	
}

fun loop() {
	GL.createCapabilities()
	
	glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
	
	while (!glfwWindowShouldClose(window)) {
		glClear(GL_COLOR_BUFFER_BIT)
		
		glLoadIdentity()
		
		dots.forEach { it.draw() }
		
		drawDot(0f, 0f, .1f, 20, 1f, 1f, 1f)
		
		glfwSwapBuffers(window)
		// key events
		glfwPollEvents()
	}
}

fun drawDot(cx: Float, cy: Float, radius: Float, segments: Int, r: Float, g: Float, b: Float) {
	glColor3f(r, g, b)
	
	glBegin(GL_TRIANGLE_FAN)
	glVertex2f(cx, cy)
	for (i in 0 .. segments) {
		glVertex2f(
			(cx + radius * cos(i * (2 * PI) / segments)).toFloat(),
			(cy + radius * sin(i * (2 * PI) / segments)).toFloat()
		)
	}
	glEnd()
}

fun Double.toRad(): Double {
	return this * 180/PI
}