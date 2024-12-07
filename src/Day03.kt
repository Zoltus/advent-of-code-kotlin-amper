fun main() {
    val liness = readInput("Day03")
    //Part 1
    val regexMul = """mul\(\d+,\d+\)""".toRegex()
    val sumOf = liness.sumOf { line ->
        val muls: Sequence<MatchResult> = regexMul.findAll(line, 0)
        muls.map { match ->
            val func = match.value
            val (left, right) = func.replace("mul(", "").replace(")", "").split(",")
            left.toInt() * right.toInt()
        }.sum()
    }
    println("Sum $sumOf")

    //Part 2
    val doRegex = """do\(\)""".toRegex()
    val dontRegex = """don't\(\)""".toRegex()

    val combinedLine = liness.joinToString()

    // All matches
    val mulList: List<MatchResult> = regexMul.findAll(combinedLine).toList()
    val doList: List<MatchResult> =  doRegex.findAll(combinedLine).toList()
    val dontList: List<MatchResult> =  dontRegex.findAll(combinedLine).toList()

    // Combine all flatted
    val combined = listOf(mulList, doList, dontList).flatten()
    // Filter order based on range
    val sortedByStartRange: List<MatchResult> = combined.sortedBy { res -> res.range.first }

    val muls = mutableListOf<MatchResult>()

    var isDo = true

    for (match in sortedByStartRange) {
        if (match.value.contains("don't()")) {
            isDo = false
        } else if (match.value.contains("do()")) {
            isDo = true
        } else {
            if (isDo) {
                 muls.add(match)
            }
        }
    }

    val mulSums = muls.sumOf { match ->
        val func = match.value
        val (left, right) = func.replace("mul(", "").replace(")", "").split(",")
        left.toInt() * right.toInt()
    }

    println("Sums: $mulSums")
}














