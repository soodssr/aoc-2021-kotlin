fun main() {
    fun part1(input: List<String>): Int {
        var gammaBinary = ""
        var epsilonBinary = ""
        var totalInput = 0
        var bitSpecific = mutableListOf<Int>()
        var indexExits: Int?
        for (i in input) {
            val binary = i.chunked(1)
            totalInput++
            for (idx in binary.indices) {
                indexExits = bitSpecific.getOrNull(idx)

                if (indexExits == null) {
                    bitSpecific.add(idx, 0)
                }
                bitSpecific[idx] += binary[idx].toInt()
            }
        }
        for (bit in bitSpecific) {
            val diff = totalInput - bit
            if (diff > bit) {
                gammaBinary += "0"
                epsilonBinary += "1"
            } else {
                gammaBinary += "1"
                epsilonBinary += "0"
            }
        }
        val gammaRate = Integer.parseInt(gammaBinary, 2)
        val epsilonRate = Integer.parseInt(epsilonBinary, 2)
        println("Gama Binary: $gammaBinary <|> Epsilon Binary: $epsilonBinary")
        println("Gama Rate: $gammaRate <|> Epsilon Rate: $epsilonRate")
        return gammaRate * epsilonRate
    }

    fun prepareData(input: List<String>, bitPosition: Int, oxygen: Boolean = true): String {
        var leadingOneBit = mutableListOf<String>()
        var leadingZeroBit = mutableListOf<String>()
        var currentBitPosition = bitPosition + 1
        for (i in input) {
            if (i[bitPosition] == '1') {
                leadingOneBit.add(i)
            } else {
                leadingZeroBit.add(i)
            }
        }
        val leadingOneCount = leadingOneBit.size
        val leadingZeroCount = leadingZeroBit.size

        if (oxygen) {
            if (leadingOneCount >= leadingZeroCount) {
                leadingZeroBit.clear()
                if (leadingOneCount > 1) return prepareData(leadingOneBit, currentBitPosition, oxygen)
                return leadingOneBit[0]
            }
            leadingOneBit.clear()
            if (leadingZeroCount > 1) return prepareData(leadingZeroBit, currentBitPosition, oxygen)
            return leadingZeroBit[0]
        }
        if (leadingOneCount >= leadingZeroCount) {
            leadingOneBit.clear()
            if (leadingZeroCount > 1) return prepareData(leadingZeroBit, currentBitPosition, oxygen)
            return leadingZeroBit[0]
        }
        leadingZeroBit.clear()
        if (leadingOneCount > 1) return prepareData(leadingOneBit, currentBitPosition, oxygen)
        return leadingOneBit[0]
    }

    fun part2(input: List<String>): Int {
        var oxygenGeneratorBinary = ""
        var co2ScrubberBinary = ""

        println("Getting Oxygen Generator Binary data now")
        oxygenGeneratorBinary = prepareData(input, 0)
        println("Getting Co2 Scrubber Binary data now")
        co2ScrubberBinary = prepareData(input, 0, false)

        val oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorBinary, 2)
        val co2ScrubberRating = Integer.parseInt(co2ScrubberBinary, 2)
        println("Oxygen Generator Binary: $oxygenGeneratorBinary <|> Co2 Scrubber Binary: $co2ScrubberBinary")
        println("Oxygen Generator Rating: $oxygenGeneratorRating <|> Co2 Scrubber Rating: $co2ScrubberRating")
        return oxygenGeneratorRating * co2ScrubberRating
    }

    val input = readFileFromInputs("Day03")
    println("Part 01 ${ part1(input) }\n")
    println("Part 02 ${ part2(input) }")
}