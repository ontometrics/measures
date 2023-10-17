package com.ontometrics.measures

trait TemperatureUnits extends MeasureUnit

enum TemperatureUnit extends TemperatureUnits {
  case Celsius
  case Fahrenheit
  case Kelvin
}

case class Temperature(val amount:Double, val temperatureUnit: TemperatureUnit)
