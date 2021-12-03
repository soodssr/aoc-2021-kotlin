fun main() {
    fun part1(input: List<String>): Int {
        var previousValue = 0;
        var depthIncreased = 0;
        for (i in input) {
            var depth = i.toInt();
            if (previousValue == 0) {
                previousValue = depth;
                continue;
            }
            if (previousValue < depth) {
                depthIncreased++;
            }
            previousValue = depth;
        }
        return depthIncreased;
    }

    fun part2(input: List<String>): Int {
        var arrangedInput = mutableListOf<String>()
        var j = 0

        for (i in input.indices) {
            if (input[i].isEmpty()) {
                continue;
            }
            var elePlusOne = input.getOrNull(i + 1)
            var elePlusTwo = input.getOrNull(i + 2)
            if (elePlusOne == null  || elePlusTwo == null || elePlusOne.isEmpty() || elePlusTwo.isEmpty()) {
                continue;
            }
            var sum = (input[i].toInt() + elePlusOne.toInt() + elePlusTwo.toInt()).toString()
            if (arrangedInput.isEmpty()) {
                arrangedInput = mutableListOf(sum)
            } else {
                arrangedInput.add(j, sum)
            }
            j++
        }
        return part1(arrangedInput);
    }

    val input = readFileFromInputs("Day01")
    println("Part 01: ${ part1(input) }")
    println("Part 02: ${ part2(input) }")
}
