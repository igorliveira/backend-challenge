terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = ">= 5.88"
    }
  }
  required_version = ">= 0.15.0"
}

provider "aws" {
  region = "us-east-1"
}

resource "aws_ecr_repository" "this" {
  name = "jwt-validator"
}

locals {
  cluster_name = "jwt-validator-eks"
}

module "vpc" {
  source = "terraform-aws-modules/vpc/aws"

  name = "my-vpc"
  cidr = "10.0.0.0/16"

  azs             = ["us-east-1a", "us-east-1b", "us-east-1c"]
  private_subnets = ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/24"]
  public_subnets  = ["10.0.101.0/24", "10.0.102.0/24", "10.0.103.0/24"]

    map_public_ip_on_launch = true
  enable_nat_gateway = false
  enable_vpn_gateway = false

  tags = {
    Terraform = "true"
    Environment = "dev"
  }
}


module "eks" {
  source = "terraform-aws-modules/eks/aws"
  version = "20.33.1"

  cluster_name = local.cluster_name
  subnet_ids     = module.vpc.public_subnets

  tags = {
    Terraform   = "true"
    Environment = "dev"
  }

  vpc_id = module.vpc.vpc_id

  # To add more nodes to the cluster, update the desired capacity.
  eks_managed_node_groups = {
    default = {
      instance_type = "t2.small"
      additional_tags = {
        Terraform = "true"
        Environment = "dev"
      }
      desired_capacity = 2
    }
  }
}

output "ecr_repository_url" {
  value = aws_ecr_repository.this.repository_url
}