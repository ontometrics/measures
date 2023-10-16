package com.ontometrics.measures

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
    toUnit match
      case this.unit => this
      case Gram => Weight(this.amount * this.unit.conversionFactorToGrams, Gram)
      case Ounce => Weight((this.amount * this.unit.conversionFactorToGrams) / toUnit.conversionFactorToGrams, Ounce)
      case Kilogram => Weight((this.amount * this.unit.conversionFactorToGrams) / toUnit.conversionFactorToGrams, Kilogram)
      case Microgram => Weight((this.amount * this.unit.conversionFactorToGrams) / toUnit.conversionFactorToGrams, Microgram)
      case Picogram => Weight((this.amount * this.unit.conversionFactorToGrams) / toUnit.conversionFactorToGrams, Picogram)
  }
}

extension (amount: Double)
  def grams: Weight = Weight(amount, Gram)
  def ounces: Weight = Weight(amount, Ounce)
  def kilograms: Weight = Weight(amount, Kilogram)
  def micrograms: Weight = Weight(amount, Microgram)
  def picograms: Weight = Weight(amount, Picogram)
