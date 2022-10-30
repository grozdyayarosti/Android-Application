package ru.permexample.objectsobmap.model

class CObject(
    var name : String,
    var description : String,
    var price : String
)
{
//    var comments : MutableList<String> = mutableListOf()
    override fun toString(): String {
        return "name $name, description: $description, price $price"
    }
}