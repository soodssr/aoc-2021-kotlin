fun main() {
    val pattern = Regex("\\s")

    fun getCommandAndUnits(input: String): Pair<String, Int> {
        val commands: List<String> = pattern.split(input);
        return Pair(commands[0], commands[1].toInt())
    }

    fun part1(input: List<String>): Int {
        var finalHorizontalPosition: Int = 0;
        var finalDepth: Int = 0;

        for (i in input) {
            val (command, units) = getCommandAndUnits(i)
            when (command) {
                "forward" -> finalHorizontalPosition += units
                "up" -> finalDepth -= units
                "down" -> finalDepth += units
                else -> println("Unknown command: $command")
            }
        }
        println("Final Horizontal Position: $finalHorizontalPosition <|> Final Depth: $finalDepth")
        return (finalHorizontalPosition * finalDepth);
    }

    fun part2(input: List<String>): Int {
        var finalHorizontalPosition: Int = 0;
        var finalDepth: Int = 0;
        var aim: Int = 0

        for (i in input) {
            val (command, units) = getCommandAndUnits(i)
            when (command) {
                "forward" -> {
                    finalHorizontalPosition += units
                    if (aim > 0) {
                        finalDepth += (aim * units)
                    }
                }
                "up" -> aim -= units
                "down" -> aim += units
                else -> println("Unknown command: $command")
            }
        }
        println("Final Horizontal Position: $finalHorizontalPosition <|> Final Depth: $finalDepth <|> Aim: $aim")
        return (finalHorizontalPosition * finalDepth);
    }

    val input = readFileFromInputs("Day02")
    println("Part 01 Final Position: ${ part1(input) }\n")
    println("Part 02 Final Position: ${ part2(input) }")
}
