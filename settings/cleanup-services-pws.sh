#!/usr/bin/env bash
cf s
cf ds -f camunda-db
cf ds -f rabbit
cf ds -f breaker
cf ds -f registry
cf ds -f config 
cf s
