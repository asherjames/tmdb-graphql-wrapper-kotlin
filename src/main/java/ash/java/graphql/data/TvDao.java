package ash.java.graphql.data;

import ash.kotlin.graphql.types.tvseason.TvSeasonType;

public interface TvDao {

    TvSeasonType getTvSeason(int tvShowId, int seasonNumber);
}
