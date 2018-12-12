import java.util.*

fun main(args: Array<String>) {

    val rand = Random()

    val maxTime = 100
    val people = ArrayList<Person>()   // 250*8が止まった駅からスタート
    for (i in 0 until 250*8){
        people.add(Person(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), Strategy.Normal))
    }


    for (time in 0 until maxTime){

    }

}


