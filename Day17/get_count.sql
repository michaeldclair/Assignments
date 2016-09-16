CREATE DEFINER=`root`@`localhost` 
PROCEDURE `get_count_for_sat`(IN the_sat INT, OUT the_count INT)

BEGIN
	
	
SELECT COUNT(*) INTO the_count FROM student where sat=the_sat;


END