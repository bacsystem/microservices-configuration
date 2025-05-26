resource "aws_s3_bucket" "s3_golang" {
  bucket = var.name_bucket
  tags = {
    Language = "Golang"
  }
  force_destroy = true
}

resource "aws_s3_object" "s3_golang_files" {
  for_each = fileset(local.object_source, "*")
  bucket = aws_s3_bucket.s3_golang.id
  key    = each.value
  source = "${local.object_source}/${each.value}"
  etag = filemd5("${local.object_source}/${each.value}")

}