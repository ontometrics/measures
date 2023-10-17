package com.ontometrics.measures

import WeightUnit.{Gram, Kilogram, Ounce}

import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers.{an, be, empty, should, shouldBe}

import scala.language.implicitConversions

class WeightTest extends AnyFeatureSpec with GivenWhenThen {

  val exampleVolume: Volume = Volume(10.0, VolumeUnit.Liter)

  Feature("Comparing Volumes") {
    Scenario("Compare volumes of same units") {
      Given("2 volumes")
      When("comparing")
      Then("1 is less than 2")
      1.liters should be < 2.liters
      2.cups should be > 1.cups

    }
    Scenario("compare volumes of differing units"){
      Given("2 volumes of")
      When("we compare the two")
      1.ounces should be > 1.grams
      Then("should work")
      }
  }
  Feature("Converting weights"){
    Scenario("we want to convert grams to ounces"){
      Given("a weight in grams")
      When("asking for them as ounces")
      val ounces = 10.grams.convertTo(Ounce)
      Then("we get a new weight in ounces")
      ounces should be (0.3527396194958041.ounces)
    }
    Scenario("we want to convert ounces to grams"){
      Given("a value as ounces")
      val weight = 2.5.ounces
      When("asking to get it as grams")
      val weight2 = weight.convertTo(Gram)
      Then("we get an equivalent amount")
      weight2 should be (70.8738078125.grams)
      }
    Scenario("convert ounces to kilograms") {
      Given("ounces")
      When("converting")
      val kgs = 1000.ounces.convertTo(Kilogram)
      Then("we get the weight in kilograms")
      kgs should be (28.349523125.kilograms)
    }
    Scenario("Random additional conversions"){
      Given("some quantities in unused units")
      val picograms = 500.picograms
      When("converting")
      val grams:Weight = picograms.convertTo(WeightUnit.Gram)
      Then("we get proper results")
      grams should be (0.000000000500.grams)
      When("micrograms are converted to picograms")
      val result:Weight = 500.picograms.convertTo(WeightUnit.Microgram)
      result should be (0.0005.micrograms)
      When("nanograms to grams")
      val inGrams = 1000000.nanograms.convertTo(WeightUnit.Gram)
      inGrams should be (.001.grams)
      }

  }
  Feature("We need to support rounding for sure"){}

  Feature("adding") {
    Scenario("adding grams") {
      Given("2 measurements in grams")
      When("adding")
      val sum = 10.grams + 20.grams
      Then("we get the sum")
      sum should be (30.grams)
    }
    Scenario("adding grams and ounces") {
      Given("a grams and ounces")
      When("adding")
      val sum = 30.grams + 1.ounces
      Then("we get the sum")
      sum should be (58.349523125000005.grams)
    }

    Scenario("adding grams to ounces") {
      Given("a grams and ounces")
      When("adding")
      val sum = 1.ounces + 30.grams
      Then("we get the sum and should be in ounces")
      sum should be(2.0582188584874124.ounces)
    }

  }
}
