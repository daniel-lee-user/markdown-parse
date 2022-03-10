for file in test-files/*.md;
do
  echo $file
  java -cp "lib/*:." MarkdownParse $file
done
