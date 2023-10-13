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
    Scenario("we want to convert grams to ounces"){
      Given("a bunch of grams")
      val implicitOunces: Ounce = Gram(1000.0)
      When("asking for them as ounces")
      val itemInOunces = Gram(1000.0).asOunces
      Then("we get a new value")
      itemInOunces should not be null
      implicitOunces should not be null
    }
    Scenario("we want to convert ounces to grams"){
      Given("a value as ounces")
      val weight = Ounce(2.5)
      When("asking to get it as grams")
      val weight2 = weight.asGrams
      Then("we get an equivalent amount")
      weight2 should be (70.8738078125.grams)

      Given("just an assignment from one unit to another")
      When("performing the assignment")
      val inGrams:Gram = weight
      Then("implicit conversion occurs")
      inGrams should be (70.8738078125.grams)
      }
  }
  Feature("We need to support rounding for sure"){}

}
