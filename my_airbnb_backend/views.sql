create materialized view accommodations_per_host as
select h.id as host_id, count(a.id) as num_accommodations
from hosts h left join accommodations a on h.id = a.host_id
group by h.id;

create materialized view hosts_per_country as
select c.name as country_name, count(h.id) as host_count
from hosts h left join countries c on h.country_id = c.id
group by c.name;