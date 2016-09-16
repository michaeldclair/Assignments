CREATE DEFINER=`root`@`localhost` PROCEDURE `increase_gpa_for_sat`
(IN the_sat INT, IN increase_amount DECIMAL(4,2))


BEGIN

	
UPDATE student SET gpa= gpa + increase_amount where sat > the_sat;


END