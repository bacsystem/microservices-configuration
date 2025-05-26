terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.84.0"
    }
  }
}

provider "aws" {
  # Configuration options
  region     = var.region_id
  access_key = var.user_ak
  secret_key = var.user_sk

  default_tags {
    tags = var.default_tags
  }


}