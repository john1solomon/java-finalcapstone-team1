#!/bin/bash
export PGPASSWORD='postgres1'
BASEDIR=$(dirname $0)
DATABASE=final_capstone
psql -U postgres -f "$BASEDIR/dropdb.sql" &&
createdb -U postgres $DATABASE &&
psql -U postgres -d $DATABASE -f "$BASEDIR/schema.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/data_users.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/data_brewer.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/data_brewery.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/data_brewery_beer.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/data_brewery_event.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/user.sql"
