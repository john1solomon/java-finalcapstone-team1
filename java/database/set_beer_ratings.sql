UPDATE brewery_beer bb
SET bayesian_rating = bbr.BAYESIAN_RATING
FROM (
		SELECT
			BB.brewery_beer_id,
			BB.num_ratings,
			BB.avg_rating,
			OVERALL.AVG_RATING,
			(((BB.num_ratings::NUMERIC / (BB.num_ratings::NUMERIC + 10)) * BB.avg_rating) + ((10 / (BB.num_ratings::NUMERIC + 10))::NUMERIC * OVERALL.AVG_RATING)) AS BAYESIAN_RATING
		FROM (
				SELECT
					'JOIN' AS JOIN_COLUMN,
					brewery_beer_id,
					num_ratings,
					avg_rating
				FROM brewery_beer
				WHERE num_ratings >= 10
			) BB
		INNER JOIN
			(
				SELECT
					'JOIN' AS JOIN_COLUMN,
					AVG(brewery_beer.avg_rating) AS AVG_RATING
				FROM brewery_beer
				WHERE brewery_beer.avg_rating >= 1
			) OVERALL
			ON BB.JOIN_COLUMN = OVERALL.JOIN_COLUMN
	) bbr
WHERE bb.brewery_beer_id = bbr.brewery_beer_id;
