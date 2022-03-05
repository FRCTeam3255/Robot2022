outdated=$(jq '[
.outdated.dependencies|.[] |
  select (
    (.group != "com.github.ben-manes.versions") and
    (.group != "org.ejml") and
    (.group != "com.fasterxml.jackson.core") and
    (.group != "junit")
  ) |
  {vendor: .group, current_version:.version, newest_version:.available.milestone}
]' build/dependencyUpdates/report.json |
  jq -c 'unique_by(.vendor)|unique_by(.newest_version) | .[]' # not the best way to dedup
)
# for i in ${outdated[@]}; do echo $i; done
# for k in $(jq -c '.[]' <<< "$outdated"); do echo $k done
if [  -n "$outdated" ]; then
    echo 'The following dependencies are out of date!'
    jq '.' <<< $outdated
    exit 1
else
    echo 'All dependencies up to date'
    exit 0
fi