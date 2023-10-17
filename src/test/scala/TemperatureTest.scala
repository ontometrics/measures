package com.ontometrics.measures

import com.ontometrics.measures.TemperatureUnit.{Celsius, Fahrenheit, Kelvin}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.{MatchResult, Matcher}
import org.scalatest.matchers.must.Matchers.{a, be, mustBe}
import org.scalatest.matchers.should.Matchers.{an, should, shouldBe}
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper
import org.scalatest.matchers.must.Matchers.convertNumericToPlusOrMinusWrapper
import org.scalatest.matchers.should.Matchers.convertNumericToPlusOrMinusWrapper

import java.math.{MathContext, RoundingMode}


class TemperatureTest extends AnyFeatureSpec with GivenWhenThen {

  Feature("should be able to make and read temperatures") {
    Scenario("we want to construct a Temperature") {
      Given("some ambient temperature")
      When("expressing it")
      Then("it is a working type")
      val ambientTemperature = Temperature(85, Fahrenheit)
      val overnightLowTemperature = Temperature(62, TemperatureUnit.Fahrenheit)
      //ambientTemperature should be > (overnightLowTemperature)
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
    }

  }


}

extension (amount: Double)
  def celsius = Temperature(amount, Celsius)

extension (t: Temperature)
  def to(unit: TemperatureUnit) =
    (t.temperatureUnit, unit) match {
      case (Fahrenheit, Celsius) => Temperature(BigDecimal((t.amount - 32) / 1.8, MathContext(3, RoundingMode.HALF_UP)).doubleValue, TemperatureUnit.Celsius)
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
