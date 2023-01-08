package ru.permexample.objectsobmap.model

class CObject(
    var name            : String,
    var period          : String,
    var price           : String
)
{
    override fun toString(): String {
        return "name $name, period: $period, price $price"
    }
}