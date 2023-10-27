DELETE 
FROM
	crime_data 
WHERE
	( start_date_time, incident_id ,offence_code) NOT IN (
	SELECT
		start_date_time,
		incident_id ,
		maxCode
	FROM
		(
		SELECT
			c.start_date_time  ,
			c.incident_id  ,
			MAX(offence_code) as maxCode
		FROM
			crime_data c 
		GROUP BY
			c.incident_id ,start_date_time
		HAVING
			count( 1 )> 1
		) aa 
	) 

AND incident_id IN ( SELECT incident_id FROM ( SELECT incident_id FROM crime_data GROUP BY incident_id HAVING count( incident_id )> 1 ) bb )
;
DELETE 
FROM
	crime_data 
WHERE
	( start_date_time, incident_id ) NOT IN (
	SELECT
		maxtime,
		id 
	FROM
		(
		SELECT
			max( c.start_date_time ) AS maxtime,
			c.incident_id AS id 
		FROM
			crime_data c 
		GROUP BY
			c.incident_id 
		HAVING
			count( c.incident_id )> 1
		) aa 
	) 

AND incident_id IN ( SELECT incident_id FROM ( SELECT incident_id FROM crime_data GROUP BY incident_id HAVING count( incident_id )> 1 ) bb )
;

UPDATE crime_data c 
SET c.dispatch_time = c.start_date_time 
WHERE
	c.dispatch_time IS NULL;
	
DELETE 
FROM
	crime_data c 
WHERE
	c.city IS NULL 
	OR c.crime_name1 IS NULL 
	OR c.crime_name2 IS NULL 
	OR c.crime_name3 IS NULL 
	OR c.incident_id IS NULL 
	OR c.offence_code IS NULL 
	OR c.start_date_time IS NULL 
	OR c.victims IS NULL;
DELETE 
FROM
	crime_data c 
WHERE
	c.start_date_time < '2020-01-01 00:00:00';
UPDATE crime_data c 
SET c.city = UPPER( c.city );

DROP TABLE
IF
	EXISTS `crimes`;
CREATE TABLE `crimes` (
	`incident_id` INT AUTO_INCREMENT primary key,
	`offence_code` INT DEFAULT NULL,
	`dispatch_time` DATETIME DEFAULT NULL,
	`victims` INT DEFAULT NULL,
	`city` VARCHAR ( 255 ) DEFAULT NULL,
	`start_date_time` DATETIME DEFAULT NULL 
);
DROP TABLE
IF
	EXISTS `offences`;
CREATE TABLE `offences` (
	`offence_code` INT primary key,
	`crime_name1` VARCHAR ( 255 ) DEFAULT NULL,
	`crime_name2` VARCHAR ( 255 ) DEFAULT NULL,
	`crime_name3` VARCHAR ( 255 ) DEFAULT NULL
);

INSERT INTO crimes SELECT
incident_id,
offence_code,
dispatch_time,
victims,
city,
start_date_time 
FROM
	crime_data c 
WHERE
	c.city IS NOT NULL 
	AND c.incident_id IS NOT NULL 
	AND c.offence_code IS NOT NULL 
	AND c.start_date_time IS NOT NULL 
	AND c.victims IS NOT NULL;

INSERT INTO offences SELECT
offence_code,
crime_name1,
crime_name2,
crime_name3 
FROM
	crime_data c 
WHERE
	c.crime_name1 IS NOT NULL 
	AND c.crime_name2 IS NOT NULL 
	AND c.crime_name3 IS NOT NULL 
	AND c.offence_code IS NOT NULL;