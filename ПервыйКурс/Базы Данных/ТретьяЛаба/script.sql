CREATE OR REPLACE FUNCTION count_of_people()
RETURNS TRIGGER AS $$
BEGIN
UPDATE space_ship
set people_count = (SELECT count(*) from human where human.ship_d =
space_ship.ship_id);
RETURN NEW ;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_count_of_people
AFTER INSERT ON human
for each row
execute procedure count_of_people()