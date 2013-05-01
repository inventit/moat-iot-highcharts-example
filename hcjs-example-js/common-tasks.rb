require 'rake/packagetask'

task :default => [:test]

#
# Setting up dependent modules
# 
# npm install -g moat
# npm install -g nodeunit
# npm install -g sinon
#
task :setup do
  system "npm link moat nodeunit sinon"
end

#
# Running NodeUnit Tests
#
task :test do
  runner = ""
  Dir.glob("*.test.js") do |f|
    runner += "\"#{f}\","
  end
  if runner.empty?
    puts "Nothing to test..."
    next
  else
    runner = "var reporter = require(\"nodeunit\").reporters.default;\nreporter.run([#{runner[0..-2]}]);"
  end
  puts "-------------Starting the following script-------------\n#{runner}"
  system "node -e '#{runner}'"
end

#
# Packaging MOAT js files (zip) for iidn jsdeploy command
#
# rake clobber_package package
#
Rake::PackageTask.new(File.basename(Dir.pwd), '1.0') do |p|
  p.need_zip = true
  p.package_files.include('*.js').exclude('*.test.js').include('*.json')
end