#!/bin/sh

java -cp opendtacho/out/production/opendtacho:opendtacho/lib/* \
  org.opendtacho.DDDQuery.Controller $*
