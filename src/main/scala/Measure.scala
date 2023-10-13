package com.ontometrics.measures

import scala.language.implicitConversions.*

sealed trait Measure


abstract class Weight(val amount: Double, val unit: WeightUnit) extends Ordered[Weight] {
  override def compare(that: Weight): Int = {
    val thisInGrams = this.amount * this.unit.conversionFactorToGrams
    val thatInGrams = that.amount * that.unit.conversionFactorToGrams
    thisInGrams.compareTo(thatInGrams)
  }
}

case class Gram(override val amount: Double) extends Weight(amount, GramUnit)

case class Ounce(override val amount: Double) extends Weight(amount, OunceUnit)

sealed abstract class WeightUnit //extends Measurement
case object GramUnit extends WeightUnit

case object Kilogram extends WeightUnit

case object OunceUnit extends WeightUnit

case object Milligram extends WeightUnit

case object Microgram extends WeightUnit

case object Nanogram extends WeightUnit

case object Picogram extends WeightUnit

extension (unit: WeightUnit) def conversionFactorToGrams: Double =
  unit match {
    case GramUnit => 1.0
    case Kilogram => 1000.0
    case OunceUnit => 28.349523125
    case Milligram => 0.001
    case Microgram => 0.000001
    case Nanogram => 0.000000001
    case Picogram => 0.000000000001
  }


extension (amount: Double)
  def grams: Weight = Gram(amount)
 // def kilograms: Weight = Kilogram(amount)
  def ounces: Weight = Ounce(amount)

given Conversion[Gram, Ounce] with
  def apply(gram: Gram): Ounce =
    Ounce(gram.amount / 28.349523125)

given Conversion[Ounce, Gram] with
  def apply(ounces:Ounce): Gram =
    Gram(ounces.amount * 28.349523125)

extension (grams: Gram)
  def asOunces: Ounce = Ounce(grams.amount/28.349523125)

extension (ounces: Ounce)
  def asGrams:Gram = Gram(ounces.amount * 28.349523125)
