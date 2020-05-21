package ch.giambo.springbootbannercreator

import org.apache.commons.cli.*
import org.springframework.boot.ansi.AnsiColor
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import kotlin.system.exitProcess

class BannerCreator {

    fun run(args: Array<String>) {
        val (inputFileName, outputFileName) = parseArgs(args)
        checkInputFile(inputFileName)
        val inputLines = FileReader(inputFileName).use { it.readLines() }
        val outputText = renderOutputText(inputLines)
        FileWriter(inputFileName, false).use { it.write(outputText) }
        println(outputText)
        println("Written to $outputFileName")
    }

    private fun parseArgs(args: Array<String>): Args {
        val options = Options()
                .addRequiredOption("i", "input", true, "Input file")
                .addRequiredOption("o", "output", true, "Output File")

        val formatter = HelpFormatter()
        val cmd: CommandLine
        try {
            cmd = DefaultParser().parse(options, args)
        } catch (e: ParseException) {
            println(e.message)
            formatter.printHelp("BannerCreator", options)
            exitProcess(-1)
        }

        if (!cmd.hasOption("input")) {
            formatter.printHelp("BannerCreator", options)
            exitProcess(-1)
        }

        return Args(cmd.getOptionValue("i"), cmd.getOptionValue("o"))
    }

    private fun checkInputFile(inputFileName: String) {
        if (!File(inputFileName).exists()) {
            println("Cannot read file $inputFileName")
            exitProcess(-1)
        }
    }

    private fun renderOutputText(inputLines: List<String>): String {
        return inputLines.mapIndexed { index, line -> Gradients.ansiColorToConsole(index) + line }
                .toMutableList()
                .also { it.add(Gradients.ansiColorToConsole(AnsiColor.DEFAULT)) }
                .joinToString(System.lineSeparator())
    }

    data class Args(
            val inputFile: String,
            val outputFile: String
    )
}