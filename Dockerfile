FROM adoptopenjdk/openjdk8:jre

RUN apt-get update

RUN apt-get install -y --no-install-recommends libgfortran3 libatlas3-base libopenblas-base

RUN ln -s /usr/lib/libfontconfig.so.1 /usr/lib/libfontconfig.so && \
    ln -s /lib/libuuid.so.1 /usr/lib/libuuid.so.1 && \
    ln -s /lib/libc.musl-x86_64.so.1 /usr/lib/libc.musl-x86_64.so.1

ENV LD_LIBRARY_PATH /usr/lib
