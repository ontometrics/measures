package com.ontometrics.measures

import TemperatureUnit.{Celsius, Fahrenheit}

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{an, should, shouldBe}

import java.math.{MathContext, RoundingMode}


class TemperatureTest extends AnyFeatureSpec with GivenWhenThen {

  Feature("should be able to make and read temperatures") {
    Scenario("we want to construct a Temperature") {
      Given("some ambient temperature")
      When("expressing it")
      Then("it is a working type")
      val ambientTemperature = Temperature(85, Fahrenheit)
      ambientTemperature.amount should be(85)
      ambientTemperature.temperatureUnit should be(TemperatureUnit.Fahrenheit)
    }

    Scenario("should be able to convert to other Units") {
      Given("a.amount in Fahrenheit")
      val currentTemperature = Temperature(85, TemperatureUnit.Fahrenheit)
      When("converting to Celsius")
      val inC = currentTemperature.to(Celsius)
      Then("we should get the right adjustment in the amount")
      inC should be (29.4.celsius)
      Given("an amount in Celsius")
      val currentTemperatureC = 29.celsius
      When("converting to F")
      val newTempInF = currentTemperatureC.to(Fahrenheit)
      newTempInF should be (84.2.fahrenheit)
    }
  }
}

extension (amount: Double)
  def celsius: Temperature = Temperature(amount.toOnePlace, Celsius)
  def fahrenheit: Temperature = Temperature(amount.toOnePlace, Fahrenheit)
  def toOnePlace: Double = BigDecimal(amount, MathContext(3, RoundingMode.HALF_UP)).doubleValue

extension (t: Temperature)
  def to(unit: TemperatureUnit): Temperature =
    (t.temperatureUnit, unit) match {
      case (Fahrenheit, Celsius) => ((t.amount - 32) / 1.8).celsius
      case (Celsius, Fahrenheit) => ((t.amount * 1.8) + 32).fahrenheit
      case _ => t
    }


//    class TemperatureMatcher(tolerance: Double, expectedValue: Temperature) extends Matcher[Temperature] {
//      def apply(left: Temperature): MatchResult = {
//        MatchResult(
//          Math.abs(left.amount - expectedValue.amount) <= tolerance,
//          s"The temperature ${left.amount} is not within ${tolerance} of ${expectedValue.amount}",
//          s"The temperature ${left.amount} is within ${tolerance} of ${expectedValue.amount}",
//          s"the temperature ${left.amount} is not within ${tolerance} of ${expectedValue.amount}",
//          s"the temperature ${left.amount} is within ${tolerance} of ${expectedValue.amount}"
//        )
//      }
//
//    }
//
//    object TemperatureMatcher {
//      def +-(tolerance: Double): TemperatureMatcher = new TemperatureMatcher(tolerance)
//    }
