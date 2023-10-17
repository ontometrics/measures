package com.ontometrics.measures

import VolumeUnit.{Cup, FluidOnce, Gallon, Liter, Milliliter, Tablespoon, Teaspoon}
import WeightUnit.*

import scala.annotation.targetName

trait Measure {
  val amount:Double
  val unit:MeasureUnit
}

trait MeasureUnit

enum WeightUnit(val conversionFactorToGrams: Double) extends MeasureUnit:
  case Gram extends WeightUnit(1.0)
  case Ounce extends WeightUnit(28.349523125)
  case Kilogram extends WeightUnit(1000.0)
  case Microgram extends WeightUnit(0.000001)
  case Nanogram extends WeightUnit( 0.000000001)
  case Picogram extends WeightUnit(0.000000000001)

case class Weight(amount: Double, unit: WeightUnit) extends Measure with Ordered[Weight] {
  override def compare(that: Weight): Int = {
    val thisInGrams =  this.convertTo(Gram).amount
    val thatInGrams = that.convertTo(Gram).amount
    thisInGrams.compareTo(thatInGrams)
  }

  @targetName("plus")
  def +(other: Weight): Weight = {
    val thisAmountInGrams = this.convertTo(Gram).amount
    val otherAmountInGrams = other.convertTo(Gram).amount
    Weight(thisAmountInGrams + otherAmountInGrams, WeightUnit.Gram).convertTo(this.unit)
  }

  def convertTo(toUnit: WeightUnit): Weight = {
    var toAmount = this.amount
    toUnit match
      case this.unit => toAmount = this.amount
      case Gram => toAmount = this.amount * this.unit.conversionFactorToGrams
      case _ => toAmount = (this.amount * this.unit.conversionFactorToGrams) / toUnit.conversionFactorToGrams

    Weight(toAmount, toUnit)
  }
}


enum VolumeUnit(val conversionFactorToLiter: Double) extends MeasureUnit:
  case Liter extends VolumeUnit(1.0)
  case FluidOnce extends VolumeUnit(0.0295735296)
  case Gallon extends VolumeUnit(3.785411784)
  case Cup extends VolumeUnit(0.23658824)
  case Tablespoon extends VolumeUnit(0.01478677)
  case Teaspoon extends VolumeUnit(0.004928921)
  case Milliliter extends VolumeUnit(0.001)

case class Volume(amount: Double, unit:  VolumeUnit) extends Measure with Ordered[Volume] {
  override def compare(that: Volume): Int = {
    val thisInLiters = this.convertTo(Liter).amount
    val thatInLiters = that.convertTo(Liter).amount
    thisInLiters.compareTo(thatInLiters)
  }

  @targetName("plus")
  def +(other: Volume): Volume = {
    val thisAmountInLiters = this.convertTo(Liter).amount
    val otherAmountInLiters = other.convertTo(Liter).amount
    Volume(thisAmountInLiters + otherAmountInLiters, VolumeUnit.Liter).convertTo(this.unit)
  }

  def convertTo(toUnit: VolumeUnit): Volume = {
    var toAmount = this.amount
    toUnit match
      case this.unit => toAmount = this.amount
      case Liter => toAmount = this.amount * this.unit.conversionFactorToLiter
      case _ => toAmount = (this.amount * this.unit.conversionFactorToLiter) / toUnit.conversionFactorToLiter
    Volume(toAmount, toUnit)
  }
}

extension (amount: Double)
  def grams: Weight = Weight(amount, Gram)
  def ounces: Weight = Weight(amount, Ounce)
  def kilograms: Weight = Weight(amount, Kilogram)
  def micrograms: Weight = Weight(amount, Microgram)
  def nanograms: Weight = Weight(amount, Nanogram)
  def picograms: Weight = Weight(amount, Picogram)
  def liters: Volume = Volume(amount, Liter)
  def fluidOunces: Volume = Volume(amount, FluidOnce)
  def gallons: Volume = Volume(amount, Gallon)
  def cups: Volume = Volume(amount, Cup)
  def tablespoons: Volume = Volume(amount, Tablespoon)
  def teaspoons: Volume = Volume(amount, Teaspoon)
  def milliliters: Volume = Volume(amount, Milliliter)

