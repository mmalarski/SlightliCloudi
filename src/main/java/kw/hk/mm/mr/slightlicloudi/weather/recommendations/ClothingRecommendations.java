package kw.hk.mm.mr.slightlicloudi.weather.recommendations;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ClothingRecommendations {
    List<Clothes> clothesList;
    List<Clothes> windyClothesList;


}
