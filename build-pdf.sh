#! /bin/bash

function convert-md-to-pdf() {
    local target_dir=$(dirname $1)
    cd $target_dir
    local source=$(basename $1)
    local target="${source%.*}.pdf"
    echo "Converting $1..."
    pandoc -V geometry:margin=3cm -o $target $source
    cd -
}

find /root/simulation_methods -name "*.md" -and -not -name "*README*" | \
    while read file; do convert-md-to-pdf "$file"; done
