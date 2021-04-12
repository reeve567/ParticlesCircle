import org.lwjgl.glfw.GLFW
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class Dot {
	object Settings {
		const val windowSize = 800
		const val dotAmount = 400
		const val speedModifier = 32
		object Color {
			const val lower = 0.4
			const val upper = .9
		}
		object Size {
			const val lower = 0.02
			const val upper = 0.04
		}
	}
	
	private val speed: Double = Random.nextDouble(0.3, 1.1)
	private val r: Float = randomColor()
	private val g: Float = randomColor()
	private val b: Float = randomColor()
	
	private val distance: Double = listOf(Random.nextDouble(0.5, 0.6), Random.nextDouble(0.2, 0.3)).random()
	private val size = Random.nextDouble(Settings.Size.lower, Settings.Size.upper).toFloat()
	
	fun draw() {
		val time = System.currentTimeMillis() / 1000.0
		val progress = ((time % 360) / (Settings.speedModifier / speed)).toRad()
		
		val x = distance * cos(progress)
		val y = distance * sin(progress)
		
		drawDot(x.toFloat(), y.toFloat(), size, 15, r, g, b)
	}
	
	private fun randomColor(): Float {
		return Random.nextDouble(Settings.Color.lower, Settings.Color.upper).toFloat()
	}
}