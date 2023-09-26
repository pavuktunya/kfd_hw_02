import  kotlin.math.*
abstract class Transport: IMovable{
    abstract val Power: Double
    override fun Move(startPlace:Vector3, finishPlace:Vector3):Double {
        val drivingTime = super.Move(startPlace, finishPlace)/Power
        //this.info()
        return drivingTime
    }
}
class Bus:Transport(){
    override val Power: Double = 120.5
    final override fun Move(startPlace:Vector3, finishPlace:Vector3):Double{
        println("Arrived a Bus Stop")
        return super.Move(startPlace, finishPlace)
    }
}
class MolniaMakvin:Transport(){
    override val Power = 18000.4
    final override fun Move(startPlace:Vector3, finishPlace:Vector3):Double{
        val time = super.Move(startPlace, finishPlace)
        println("Kchay! Your guys in ${time} seconds")
        return time
    }
    override fun info(){
        println("KCHAY")
    }
}
class Train:Transport(){
    override val Power = 5.8
    final override fun Move(startPlace:Vector3, finishPlace:Vector3):Double{
        val time =super.Move(startPlace, finishPlace)
        println("Train arrived asbkdbhsjhdvjhads: ${time}")
        return time
    }
    override fun info(){
        super.info()
        println("Its TRAIN GUYS")
    }
}
sealed interface IMovable{
    fun info(){
        println("I can move!")
    }
    fun Move(startPlace: Vector3, finishPlace: Vector3) = startPlace.Distance(finishPlace)
}
interface IMove:IMovable
object ApplicateAxis{
    val z_axis=0.0;
}
data class Vector(val positionX: Double,val positionY: Double, val positionZ: Double){
    fun Lenght() = sqrt(positionX*positionX+positionY*positionY+positionZ*positionZ)
}
open class Vector3(val positionX: Double,val positionY: Double, val positionZ: Double){
    constructor(_vector3: Vector):this(_vector3.positionX,_vector3.positionY,_vector3.positionZ)
    val _vector3=Vector(this.positionX, this.positionY, this.positionZ)
    fun Distance(nextPosX:Double, nextPosY: Double, nextPosZ: Double ) =
        Vector(positionX-nextPosX, positionY-nextPosY, positionZ-nextPosZ).Lenght()
    fun Distance(nextVect3: Vector3)=
        Distance(nextVect3._vector3.positionX, nextVect3._vector3.positionY, nextVect3._vector3.positionZ)
}
class Vector2(positionX: Double, positionY: Double):Vector3(positionX, positionY, ApplicateAxis.z_axis)
fun main(args: Array<String>) {
    println("Input number of Transport:")
    println("1 -> Bus")
    println("2 -> Train")
    println("3 -> MolniaMakvin")
    var number:Int? =readln().toIntOrNull()

    while (number !in 1..3){
        println("Are you okay, bro?")
        println("Try again:")
        number =readln().toIntOrNull()
    }
    val transport:Transport = when(number){
        1->Bus()
        2->Train()
        3->MolniaMakvin()
        else -> MolniaMakvin()
    }
    transport.info()
    val state1=Vector3(1.0,2.0,3.0)
    val state2=Vector3(21000.0,15455.0,6.9)
    println("Seconds left: ${transport.Move(state1, state2)}")
}
