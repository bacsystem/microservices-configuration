output "name_bucket" {
  value = aws_s3_bucket.s3_golang.id                                         # The actual value to be outputted
}

output "sk" {
  value     = var.user_ak
  sensitive = true
}