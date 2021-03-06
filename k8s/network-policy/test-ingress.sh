#!/bin/bash

function test() {

    output=$(curl --max-time 1 -s $2 )
    if [[ $output == *$3* ]]
    then
        echo $1": OK"
    else
        echo $1:" FAIL"
    fi
}


HOST=$(minikube ip)

echo Testing serviceA from http://$HOST/servicea/

test "ServiceA External Ingress" "http://$HOST/servicea/internalvalue" "{ value: 0 }"

test "ServiceA External Egress" "http://$HOST/servicea/externalvalue" "0747532699"

test "ServiceA to ServiceB" "http://$HOST/servicea/servicebvalue-internal" "{ value: 0 }"

test "ServiceB External Egress (through ServiceA)" "http://$HOST/servicea/servicebvalue-external" "0747532699"




