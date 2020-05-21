package ch.giambo.springbootbannercreator

import org.springframework.boot.ansi.AnsiColor

object Gradients {

    val longGradiend = listOf(
            AnsiColor.BRIGHT_CYAN,
            AnsiColor.CYAN,
            AnsiColor.BRIGHT_GREEN,
            AnsiColor.GREEN,
            AnsiColor.YELLOW,
            AnsiColor.RED,
            AnsiColor.BRIGHT_RED,
            AnsiColor.BRIGHT_YELLOW,
            AnsiColor.BRIGHT_MAGENTA,
            AnsiColor.MAGENTA,
            AnsiColor.BLUE,
            AnsiColor.BRIGHT_BLUE
    )

    fun ansiColorToConsole(ansiColor: AnsiColor) = "\u001B[${ansiColor}m"

    fun ansiColorToConsole(gradientIndex : Int) : String {
        val ansiColor = longGradiend[gradientIndex % longGradiend.size]
        return ansiColorToConsole(ansiColor)
    }
}