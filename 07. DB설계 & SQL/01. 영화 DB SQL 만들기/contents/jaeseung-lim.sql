SELECT @@sql_mode;
SET SESSION sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- 1. 영화 '퍼스트 맨'의 제작 연도, 영문 제목, 러닝 타임, 플롯을 출력하세요.
SELECT ReleaseYear, Title, RunningTime, Plot
FROM Movie
WHERE KoreanTitle = '퍼스트 맨';

-- 2. 2003년에 개봉한 영화의 한글 제목과 영문 제목을 출력하세요
SELECT KoreanTitle, Title
FROM Movie
WHERE ReleaseYear = '2003';

-- 3. 영화 '글래디에이터'의 작곡가를 고르세요
-- 글래디에이터 -> Interstellar 변경
SELECT Name
FROM Movie
	INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
WHERE RoleName = 'Composer' AND Title='Interstellar';

-- 4. 영화 '매트릭스'의 감독이 몇명인지 출력하세요
-- 매트릭스 -> aya 변경
SELECT Count(*)
FROM Movie
	INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
WHERE RoleName = 'Director' AND Title = 'aya';

-- 5. 감독이 2명 이상인 영화를 출력하세요
SELECT Title
FROM Movie
	INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
WHERE RoleName = 'Director'
	GROUP By Title
	HAVING COUNT(*) >= 2;

-- 6. '한스 짐머'가 참여한 영화 중 아카데미를 수상한 영화를 출력하세요
-- 한스 짐머 -> 알렉산드레 데스플랫 변경
SELECT Title
FROM Movie
	INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
    INNER JOIN AwardInvolve ON Appear.AppearID = AwardInvolve.AppearID
    INNER JOIN AwardYear ON AwardInvolve.AwardYearID = AwardYear.AwardYearID
    INNER JOIN Award ON AwardYear.AwardID = Award.AwardID
WHERE KoreanName = '알렉산드레 데스플랫' AND AwardKoreanTitle = '아카데미 영화제';

-- 7. 감독이 '제임스 카메론'이고 '아놀드 슈왈츠네거'가 출연한 영화를 출력하세요
Select Title
FROM Movie
	INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
WHERE RoleName = 'Director' AND Name = '제임스 카메론' AND RoleName = 'Actor' AND Name = '아놀드 슈왈츠네거';

-- 8. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 고르시오
Select Title
FROM Movie
	INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
WHERE RunningTime >= 100 AND RoleName = 'Actor' AND Name = '레오나르도 디카프리오';

-- 9. 미성년자 관람불가 등급의 영화 중 가장 많은 수익을 얻은 영화를 고르시오
SELECT Title
FROM Movie
	INNER JOIN GradeInKorea ON Movie.GradeInKoreaID = GradeInKorea.GradeInKoreaID
WHERE GradeInKorea.GradeInKoreaName = '청소년 관람불가' 
	ORDER BY BoxOfficeWWGross DESC
	LIMIT 1;

-- 10. 1999년 이전에 제작된 영화의 수익 평균을 고르시오
SELECT avg(BoxOfficeWWGross)
FROM Movie
WHERE ReleaseYear < 1999;

-- 11. 가장 많은 제작비가 투입된 영화를 고르시오.
SELECT Title
FROM Movie
	ORDER BY Budget DESC
	LIMIT 1;

-- 12. 제작한 영화의 제작비 총합이 가장 높은 감독으로 고르시오
SELECT Name, sum(BUDGET)
FROM Movie
	INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
WHERE RoleName = 'Director'
	GROUP BY Name
	LIMIT 1;

-- 13. 총 영화 수입이 가장 많은 배우를 출력하세요.
SELECT Name, sum(BoxOfficeWWGross) AS SUM
FROM Movie
	INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
WHERE RoleName = 'Actor'
	GROUP BY Name
    ORDER BY SUM DESC
    LIMIT 1;
    
-- 14. 제작비가 가장 적게 투입된 영화의 수익을 고르세요.
SELECT Budget
FROM Movie
WHERE NOT Budget is NULL
	ORDER BY Budget
    LIMIT 1;

-- 15. 제작비가 5000만 달려 이하인 영화의 평균 수익을 고르세요
SELECT avg(Budget)
FROM Movie
WHERE Budget <= 50000000;

-- 16. 액션 장르 영화의 평균 수익을 고르세요
SELECT avg(BoxOfficeWWGross)
FROM Movie
	INNER JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID
    INNER JOIN Genre On MovieGenre.GenreID = Genre.GenreID
WHERE GenreName = 'Action';

-- 17. 드라마, 전쟁 장르의 영화를 고르세요.
SELECT Title
FROM Movie
	INNER JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID
    INNER JOIN Genre On MovieGenre.GenreID = Genre.GenreID
WHERE GenreName = 'Drama' OR GenreName = 'War';

-- 18. 톰 행크스가 출연한 영화 중 상영 시간이 가장 긴 영화를 고르세요
SELECT Title
FROM Movie
	INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Person ON Appear.PersonID = Person.PersonID
WHERE KoreanName = '톰 행크스'
	ORDER BY RunningTime DESC
    LIMIT 1;

-- 19. 아카데미 남우주연상을 가장 많이 수상한 배우를 고르시오
SELECT Name, count(Sector.SectorID) AS Award_Count
FROM Person
	INNER JOIN Appear ON Person.PersonID = Appear.PersonID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN AwardInvolve ON Appear.AppearId = AwardInvolve.AppearId
	INNER JOIN Sector ON AwardInvolve.SectorId = Sector.SectorId
WHERE SectorName = 'Best Actor in a Leading Role'
	GROUP BY Name
    ORDER BY Award_Count DESC
    Limit 1;
    
-- 20.아카데미상을 가장 많이 수상한 영화인을 고르시오
SELECT Name, count(name) AS Award_Count
FROM Person
	INNER JOIN Appear ON Person.PersonID = Appear.PersonID
    INNER JOIN AwardInvolve ON Appear.AppearID = AwardInvolve.AppearID
    INNER JOIN AwardYear ON AwardInvolve.AwardYearID = AwardYear.AwardYearID
    INNER JOIN Award ON AwardYear.AwardID = Award.AwardID
WHERE AwardKoreanTitle = '아카데미 영화제'
	GROUP BY Name
    ORDER BY Award_Count DESC
    LIMIT 1;
    
-- 21. 아카데미 남우주연상을 2번 이상 수상한 배우를 고르시오
-- 미완성
SELECT Name, count(Name) AS Award_Count
FROM Person
	INNER JOIN Appear ON Person.PersonID = Appear.PersonID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN AwardInvolve ON Appear.AppearId = AwardInvolve.AppearId
	INNER JOIN Sector ON AwardInvolve.SectorId = Sector.SectorId
-- WHERE SectorName = 'Best Actor in a Leading Role'
-- WHERE count(name) >= 2
	GROUP BY Name;
    
-- 23. 아카데미상을 가장 많이 수상한 사람을 고르세요.
SELECT Name, count(Name) As Award_Count
FROM Person
	INNER JOIN Appear ON Person.PersonID = Appear.PersonID
    INNER JOIN AwardInvolve ON Appear.AppearID = AwardInvolve.AppearID
    INNER JOIN AwardYear ON AwardInvolve.AwardYearID = AwardYear.AwardYearID
    INNER JOIN Award ON AwardYear.AwardID = Award.AwardID
	GROUP BY Name
    ORDER BY Award_count DESC
    LIMIT 1;
    
-- 24. 아카데미상에 가장 많이 노미네이트 된 영화를 고르세요.
SELECT Title, count(Title) AS Award_Count
FROM Movie
	INNER JOIN Appear ON Movie.MovieID = Appear.MovieID
    INNER JOIN AwardInvolve ON Appear.AppearID = AwardInvolve.AppearID
    INNER JOIN AwardYear ON AwardInvolve.AwardYearID = AwardYear.AwardYearID
    INNER JOIN Award ON AwardYear.AwardID = Award.AwardID
WHERE AwardKoreanTitle = '아카데미 영화제'
	GROUP BY Title
    ORDER BY Award_Count DESC
    LIMIT 1;
    
-- 25. 가장 많은 영화에 출연한 여배우를 고르세요.
SELECT Name, count(Name) As Actress_Name
FROM Person
	INNER JOIN Appear ON Person.PersonID = Appear.PersonID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
WHERE RoleName = 'Actress'
	GROUP BY Name
    ORDER BY Actress_Name DESC
    LIMIT 1;
    
-- 26. 수익이 가장 높은 영화 TOP 10을 출력하세요. 
SELECT Title
FROM Movie
ORDER BY BoxOfficeWWGross DESC
LIMIT 10;

-- 27. 수익이 10억불 이상인 영화중 제작비가 1억불 이하인 영화를 고르시오.
SELECT Title
FROM Movie
WHERE BoxOfficeWWGross >= 1000000000 AND Budget <= 100000000;

-- 28. 전쟁 영화를 가장 많이 감독한 사람을 고르세요.
SELECT Name
FROM Person
	INNER JOIN Appear ON Person.PersonID = Appear.PersonID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Movie ON Appear.MovieID = Movie.MovieID
    INNER JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID
    INNER JOIN Genre ON MovieGenre.GenreID = Genre.GenreID
WHERE GenreName = 'War' AND RoleName = 'Director'
	GROUP BY Name
    ORDER BY count(Name) DESC
    LIMIT 1;

-- 29. 드라마에 가장 많이 출연한 사람을 고르세요.
SELECT Name
FROM Person
	INNER JOIN Appear ON Person.PersonID = Appear.PersonID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Movie ON Appear.MovieID = Movie.MovieID
    INNER JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID
    INNER JOIN Genre ON MovieGenre.GenreID = Genre.GenreID
WHERE GenreName = 'Drama' AND RoleName = 'Actor'
	GROUP BY Name
    ORDER BY count(Name) DESC
    LIMIT 1;

-- 30. 드라마 장르에 출연했지만 호러 영화에 한번도 출연하지 않은 사람을 고르세요.
SELECT Name
FROM Person
	INNER JOIN Appear ON Person.PersonID = Appear.PersonID
	INNER JOIN Role ON Appear.RoleID = Role.RoleID
    INNER JOIN Movie ON Appear.MovieID = Movie.MovieID
    INNER JOIN MovieGenre ON Movie.MovieID = MovieGenre.MovieID
    INNER JOIN Genre ON MovieGenre.GenreID = Genre.GenreID
WHERE GenreName = 'Drama' AND GenreName NOT IN ('Horror');

-- 31. 아카데미 영화제가 가장 많이 열린 장소는 어디인가요?
SELECT Location
FROM AwardYear
	GROUP BY Location
    ORDER BY count(Location) DESC
    LIMIT 1;
    
-- 33. 첫 번째 아카데미 영화제가 열린지 올해 기준으로 몇년이 지났나요?
SELECT concat(Year(current_timestamp()) - Year, ' 년')
FROM AwardYear
ORDER BY Date
LIMIT 1;