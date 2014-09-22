package com.noctis.tools.farm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.noctis.tools.core.serialize.PlantableDeserializer;
import com.noctis.tools.core.serialize.PlantableSerializer;

@JsonSerialize(using = PlantableSerializer.class)
@JsonDeserialize(using = PlantableDeserializer.class)
public enum Plantable {

  ALOE(51480000L, FarmType.SEED, FarmType.Climate.ARID),
  AZALEA(1740000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  BARLEY(2580000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  BEAN(92520000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  BLUEBERRY(25200000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  CACTUS(20580000L, FarmType.SEED, FarmType.Climate.ARID),
  CARROT(2580000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  CHILI_PEPPER(51480000L, FarmType.SEED, FarmType.Climate.ARID),
  CLOVER(3600000L, FarmType.SEED, FarmType.Climate.ALL_CLIMATES),
  CLUBHEAD_FUNGUS(185160000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  CORN(10320000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  CORNFLOWER(20580000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  COTTON(10320000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  CUCUMBER(600000L, FarmType.SEED, FarmType.Climate.ALL_CLIMATES),
  CULTIVATED_GINSENG(123480000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  GARLIC(5160000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  IRIS(5160000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  JINNS_TONGUE(51480000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  LAVENDER(5160000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  LILY(10320000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  LOTUS(14400000L, FarmType.SEED, FarmType.Climate.ALL_CLIMATES),
  MILLET(10320000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  MINT(15480000L, FarmType.SEED, FarmType.Climate.ARID),
  MUSHROOM(7200000L, FarmType.SEED, FarmType.Climate.ALL_CLIMATES),
  NARCISSUS(5160000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  OAT(15480000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  ONION(2580000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  PEANUT(41160000L, FarmType.SEED, FarmType.Climate.TROPICAL),
  PEPPER(51480000L, FarmType.SEED, FarmType.Climate.TROPICAL),
  POPPY(123480000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  POTATO(600000L, FarmType.SEED, FarmType.Climate.ALL_CLIMATES),
  PUMPKIN(15480000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  QUINOA(92520000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  RICE(10320000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  ROSE(5160000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  ROSEMARY(10800000L, FarmType.SEED, FarmType.Climate.ALL_CLIMATES),
  RYE(41160000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  SAFFRON(123480000L, FarmType.SEED, FarmType.Climate.ARID),
  STRAWBERRY(15480000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  SUNFLOWER(14400000L, FarmType.SEED, FarmType.Climate.ALL_CLIMATES),
  THISTLE(20580000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  TOMATO(5160000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  TURMERIC(20580000L, FarmType.SEED, FarmType.Climate.ARID),
  VANILLA(51480000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  WHEAT(15480000L, FarmType.SEED, FarmType.Climate.TEMPERATE),
  YAM(15480000L, FarmType.SEED, FarmType.Climate.TEMPERATE),

  PINE(100000L, FarmType.SAPLING, FarmType.Climate.TEMPERATE),

  BLIZZARD_BEAR(111600000L, FarmType.LIVESTOCK, FarmType.Climate.SUBARCTIC),
  CASHMERE_YATTA(36000000L, FarmType.LIVESTOCK, FarmType.Climate.ARID),
  CHICKEN(10320000L, FarmType.LIVESTOCK, FarmType.Climate.TEMPERATE),
  COW(122400000L, FarmType.LIVESTOCK, FarmType.Climate.TEMPERATE),
  DUCK(10320000L, FarmType.LIVESTOCK, FarmType.Climate.TEMPERATE),
  GOAT(122400000L, FarmType.LIVESTOCK, FarmType.Climate.TEMPERATE),
  GOOSE(20580000L, FarmType.LIVESTOCK, FarmType.Climate.TEMPERATE),
  PIG(51480000L, FarmType.LIVESTOCK, FarmType.Climate.TEMPERATE),
  SHEEP(51480000L, FarmType.LIVESTOCK, FarmType.Climate.TEMPERATE),
  TURKEY(20580000L, FarmType.LIVESTOCK, FarmType.Climate.TEMPERATE),
  WATER_BUFFALO(122400000L, FarmType.LIVESTOCK, FarmType.Climate.TROPICAL);

  private Long growthTime;

  private String type;

  private FarmType.Climate climate;

  private Plantable(Long growthTime, String type, 
      FarmType.Climate climate) {
    this.growthTime = growthTime;
    this.type = type;
    this.climate = climate;
  }
  
  public Long getGrowthTime() {
    return growthTime;
  }

  public void setGrowthTime(Long growthTime) {
    this.growthTime = growthTime;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public FarmType.Climate getClimate() {
    return climate;
  }

  public void setClimate(FarmType.Climate climate) {
    this.climate = climate;
  }

}
