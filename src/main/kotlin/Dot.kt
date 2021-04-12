import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class Dot {
	object Settings {
		const val windowSize = 1000
		const val dotAmount = 3000
		const val speedModifier = 45
		object Color {
			const val lower = 0.4
			const val upper = 1.0
		}
		object Size {
			const val lower = 0.0025
			const val upper = 0.008
		}
		
		fun threeRings(): Double {
			return listOf(Random.nextDouble(0.75, 0.9), Random.nextDouble(0.45, 0.65), Random.nextDouble(0.2, 0.35)).random()
		}
		fun oneBig(): Double {
			return Random.nextDouble(0.2, 0.9)
		}
	}
	
	private val r: Float = randomColor()
	private val g: Float = randomColor()
	private val b: Float = randomColor()
	
	private val speed: Double = Random.nextDouble(0.3, 1.1)
	private val distance: Double = Settings.threeRings()
	private val size = Random.nextDouble(Settings.Size.lower, Settings.Size.upper).toFloat()
	
	fun draw() {
		val time = System.currentTimeMillis() / 1000.0
		val progress = (time / (Settings.speedModifier / speed).toFloat()).toRad()
		
		val x = distance * cos(progress)
		val y = distance * sin(progress)
		
		drawDot(x.toFloat(), y.toFloat(), size, 15, r, g, b)
	}
	
	private fun randomColor(): Float {
		return Random.nextDouble(Settings.Color.lower, Settings.Color.upper).toFloat()
	}
}