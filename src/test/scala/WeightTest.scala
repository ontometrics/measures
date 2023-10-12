package com.ontometrics.measures

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.must.Matchers.not
import org.scalatest.matchers.should.Matchers.{an, be, empty, should, shouldBe}

import scala.language.implicitConversions

class WeightTest extends AnyFeatureSpec with GivenWhenThen {

  Feature("Comparing Weights") {
    Scenario("Compare 2 weights of units grams") {
      Given("2 different weights")
      val measure1 = 1.grams
      val measure2 = 2.grams
      When("comparing")
      Then("1 is less than 2")
      measure1 should be < measure2
    }
    Scenario("compare 2 weights of differing units"){
      Given("an ounce and a gram")
      When("we compare the two")
      1.ounces should be > 1.grams
      Then("should work")
      }
  }
  Feature("Converting weights"){
    val implicitOunces: Ounce = Gram(1000.0)
    val itemInOunces = Gram(1000.0).asOunces
    itemInOunces should not be null
    implicitOunces should not be null
  }

}
