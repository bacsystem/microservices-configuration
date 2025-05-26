resource "aws_dynamodb_table" "db_golang" {
  name           = var.name_dynamodb
  read_capacity  = 20
  write_capacity = 20
  hash_key       = "FileId"
  //range_key = "FileType"

  //Attributes - (Required) Attribute type. Valid values are S (string), N (number), B (binary).
  attribute {
    name = "FileId"
    type = "S"
  }

  /*
    attribute {
      name = "FileType"
      type = "S"
    }
    attribute {
      name = "Content"
      type = "S"
    }

   */

  ttl {
    attribute_name = "TimeToExist"
    enabled        = true
  }

  tags = {
    Language = "Golang"
  }

}