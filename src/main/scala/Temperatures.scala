package com.ontometrics.measures

trait MeasureUnit
trait TemperatureUnits extends MeasureUnit

enum TemperatureUnit extends TemperatureUnits {
  case Celsius
  case Fahrenheit
  case Kelvin
}

case class Temperature(amount:Double, temperatureUnit: TemperatureUnit)
