provider "aws" {
  region = "us-east-1"
}

# Create Security group
resource "aws_security_group" "securitygroup" {
  name        = "ec2-securitygroup"
  description = "Ingress Http and SSH and Egress to anywhere "
  vpc_id                 = "vpc-0a82a6839c243554a"

# Defining application port
  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

# Defining ec2 access port
  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

# Defining output port - not using
  egress {
    from_port   = 0
    to_port     = 65535
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Create Instance 
resource "aws_instance" "ec2" {
  ami                    = "ami-0c101f26f147fa7fd"
  instance_type          = "t2.nano"
  user_data              = file("user_data.sh")
  vpc_security_group_ids = ["${aws_security_group.securitygroup.id}"]
  subnet_id              = "subnet-0281ea7a003e29920"
}