package org.cli.registry

import org.cli.Steps

class Registry implements RegistryInterface{
    String region
    String accountid
    String access_key
    String secret_key
    Steps link = new Steps()
    String store

    Registry(String accountid = "133320081649", String region="eu-west-1",String access_key="AKIAR6CT7ADYSIBLV3MI",String secret_key="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"){
        this.region = region
        this.accountid = accountid
        this.access_key = access_key
        this.secret_key = secret_key
    }
    @Override
    def token() {
        String label = "getting token for ECR repo"
        String command = "aws ecr get-login-password --region $region"
        this.store = link.withenvstd(["AWS_ACCESS_KEY_ID=$access_key","AWS_SECRET_ACCESS_KEY=$secret_key","AWS_DEFAULT_REGION=${region}"],label,command)
        println(this.store)
    }
}

