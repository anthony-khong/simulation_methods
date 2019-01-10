FROM jagregory/pandoc

ADD build-pdf.sh /root/build-pdf.sh

ENTRYPOINT bash -c /root/build-pdf.sh
